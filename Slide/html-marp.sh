#!/bin/bash

MD_FILE=$1

if [ -z "$MD_FILE" ]; then
  echo "Usage: $0 <markdown-file>"
  exit 1
fi

OUTPUT_DIR="slides"
THEME_CSS="nlh-theme.css"

mkdir -p "$OUTPUT_DIR"

OUT_FILE="$OUTPUT_DIR/$(basename "${MD_FILE%.*}").html"

marp "$MD_FILE" --html --allow-local-files --theme "$THEME_CSS" --output "$OUT_FILE"
