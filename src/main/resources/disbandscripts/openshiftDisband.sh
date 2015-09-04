#! /bin/sh

echo "________________________________"
echo "Deleting app $1"
echo "________________________________"
/usr/local/bin/rhc delete-app $1 --confirm
rm -rf ~/thesis/startup/$1/
echo "________________________________"
echo "$1 - deleted"
echo "________________________________"