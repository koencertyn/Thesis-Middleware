#! /bin/sh

: '
 $1 describes the name of the platform you want to boot.
 $2 declares the LOCAL directory where you want to boot the cloud from.
 $3 declares the LOCAL directory from where you can copy the code from.

 example execution : sh herokuBoot.sh testje-jaja ~/test /Users/koencertyn/workspace/Middleware
'
echo "called"
heroku create $1
echo "called"
cd $2
echo "called"
mkdir $1
cp -r $3/* $2/$1
cd $1
echo "________________________________"
echo "Initialising git"
echo "________________________________"
git init
heroku git:remote -a $1
echo "________________________________"
echo "Deploying application $1"
echo "________________________________"
git add -A
git commit -am "deployment"
git push heroku master