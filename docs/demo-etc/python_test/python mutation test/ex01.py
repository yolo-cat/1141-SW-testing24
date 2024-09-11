

t1=[1,2,3]
t2=[1,2,1]
t3=[3,1,2]
t4=[10,9,8]

def p(a):
    r = 0
    for i in [1,2]:
        if a[i] > a[r]:
            r = i
    return r

def p1(a):
    r = 0
    for i in [0,1,2]:
        if a[i] > a[r]:
            r = i
    return r

def p2(a):
    r = 0
    for i in [1,2]:
        if i > a[r]:
            r = i
    return r

def p3(a):
    r = 0
    for i in [1,2]:
        if a[i] >= a[r]:
            r = i
    return r

def p4(a):
    r = 0
    for i in [1,2]:
        if True:
            r = i
    return r

t = [t1, t2, t3,t4]
mu = [p1, p2, p3, p4]

for tc in t:
    print ('Test case: ', tc)
    print ('P : ', p(tc), end='\t')
    print ('P1: ', p1(tc), end='\t')
    print ('P2: ', p2(tc), end='\t')
    print ('P3: ', p3(tc), end='\t')
    print ('P4: ', p4(tc), end='\t')
    for m in mu:
        if p(tc) != m(tc):
            print ('\n Kill the mutation', m)
    print ()


