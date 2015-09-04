#! /bin/sh

: '
 $1 describes the name of the platform you want to boot.
 $2 declares the LOCAL directory where you want to boot the cloud from.
 $3 declares the LOCAL directory from where you can copy the code from.

example execution : sh openshiftBoot.sh demokoencerty01 /Users/koencertyn/thesis/startup /Users/koencertyn/thesis/boot/openshift

'
cd $2
echo "Booting $1"
/usr/local/bin/rhc create-app $1 jbossas
cd $1
echo "Booted $1"
echo "________________________________"
echo "Removing default files"
echo "________________________________"
rm README.md
rm -r deployments
rm pom.xml
rm -r src
echo "________________________________"
echo "Copying new files from $3 into folder $2/$1"
echo "________________________________"
cp -r $3/* $2/$1
git add -A
git commit -am "deploying"
git push
echo "________________________________"
echo "Starting deployment of Openshift cloud"
echo "________________________________"
