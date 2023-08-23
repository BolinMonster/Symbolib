#!/bin/bash

if [ $# -lt 1 ] || [ $# -gt 2 ]; then
    echo "Usage: $0 <filename> [<value>]"
    exit 1
fi

filename="$1"
value="$2"

if [ ! -f "$filename" ]; then
    echo "Erreur: Le fichier $filename n'existe pas."
    exit 1
fi

if [ -z "$value" ]; then
    java Calc "$filename"
else
    java Calc "$filename" "$value"
fi
