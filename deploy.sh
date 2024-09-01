#!/bin/zsh
set -e

echo '----------- Generate dist.tar.gz archive -----------'

tar czvf dist.tar.gz dist/

echo '----------- Copy dist.tar.gz to helios.cs.ifmo.ru -----------'

scp -P 2222 dist.tar.gz s333057@helios.cs.ifmo.ru:/home/studs/s333057/public_html/

echo '----------- Untar dist.tar.gz into ~/public_html/dist/ -----------'

ssh s333057@helios.cs.ifmo.ru -p 2222 "cd ~/public_html/ && rm -rf dist/ && tar xvf dist.tar.gz && rm dist.tar.gz"

echo '----------- Cleanup archive -----------'

rm dist.tar.gz

echo '----------- Open https://se.ifmo.ru/~s333057/dist/ -----------'

open -a "Arc" "https://se.ifmo.ru/~s333057/dist/"
