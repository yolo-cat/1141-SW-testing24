package exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class USAElection {
        public static Object[] getElectionResult(List<Object[]> electionData) {
                String winner = "NONE";
                String AlignOrSplit = "NONE";
                Map<String, int[]> state_votes = new HashMap<>();
                String candidate1="-", candidate2="-";
                for (Object[] fields: electionData) {
                        if (((String)fields[0]).equals("State")) {
                                candidate1 = (String)fields[2];
                                candidate2 = (String)fields[3];
                                continue;
                        }
                        // handle each data
                        int[] v = {
                                Integer.parseInt((String)fields[1]),
                                Integer.parseInt((String)fields[2]),
                                Integer.parseInt((String)fields[3])
                        };
                        state_votes.put((String)fields[0], v);
                }
                int c1_popular_votes = 0;       // 一號候選人的人口票
                int c2_popular_votes = 0;       // 二號候選人的人口票
                int c1_electoral_votes = 0;     // 一號候選人的選舉人票
                int c2_electoral_votes = 0;     // 二號候選人的選舉人票

                for (Map.Entry<String, int[]> voteEntry: state_votes.entrySet()) {
                        String state = voteEntry.getKey();
                        int[] vote_arr = voteEntry.getValue();
                        c1_popular_votes += vote_arr[1];
                        c2_popular_votes += vote_arr[2];
                        if (vote_arr[1] > vote_arr[2])
                                c1_electoral_votes += vote_arr[0];
                        else if (vote_arr[2] > vote_arr[1])
                                c2_electoral_votes += vote_arr[0];
                }

                int winner_electoral_votes = 0;
                if (c1_electoral_votes > c2_electoral_votes) {
                        winner = candidate1;
                        winner_electoral_votes = c1_electoral_votes;
                        AlignOrSplit = (c1_popular_votes > c2_popular_votes) ? "Align" : "Split";
                }
                else if (c2_electoral_votes > c1_electoral_votes) {
                        winner = candidate2;
                        winner_electoral_votes = c2_electoral_votes;
                        AlignOrSplit = (c2_popular_votes > c1_popular_votes) ? "Align" : "Split";
                }
                else
                        winner_electoral_votes = -1;
                Object[] result = {winner, winner_electoral_votes, AlignOrSplit};
                return result;
        }
}