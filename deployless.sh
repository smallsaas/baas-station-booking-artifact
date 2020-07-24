#!/usr/bin/bash

mod=$1

## host ##
#target='ems@mall.smallsaas.cn:/home/ems/am'
target='root@zchengb.xyz:/root/zxchengb/temp'

#### split from target  below ### 
app_path=${target##*:}  ## cur before :
ssh_host=${target%%:*}
####


## debug
#echo ssh_host= $ssh_host 
#echo app_path= $app_path
#echo ssh $ssh_host \"cd $app_path/api \&\& sh docker-deploy-lib.sh\"
#exit
## end debug



if [ ! $mod ];then
   echo 'Usage: deployless <module>'
   echo '  e.g. deployless am-fault'
   exit
fi

if [ ! -d  ../$mod ];then
   if [ ! -d $mod ];then 
     echo module $mod not exists
     exit
   fi
else
   cd ..
fi


## deploy web
deploy_web() {
    ## means web, check dist
   if [ ! -d dist ];then
      echo you try to deply web, but dist not exists
      exit
   fi

   ## package dist with tar
   echo tar -cvf dist.tar dist 
   tar -cvf dist.tar dist
   echo scp dist.tar $target/web
   scp dist.tar $target/web
   ## clean after scp
   echo rm dist.tar
   rm dist.tar

   echo ssh $ssh_host \"cd $app_path/web \&\& sh deploy.sh\"
   ssh $ssh_host "cd $app_path/web && sh deploy.sh"
}


## deploy lib
deploy_lib() {
  list=()
  for jar in $(ls target/*.jar);do
     if [ $jar == target/*standalone.jar ];then
        echo $jar >/dev/null
     else
       list="$list $jar"
     fi
  done

  if [ ! $list ];then 
     echo no .jar found !
     exit
  fi


  echo scp $list $target/api/lib
  scp $list $target/api/lib
  echo ssh $ssh_host \"cd $app_path/api \&\& sh docker-deploy-lib.sh\"
  ssh $ssh_host "cd $app_path/api && sh docker-deploy-lib.sh"
}


## main  ##
cd $mod  ## go into module

if [ -f package.json ];then
   deploy_web
else
   deploy_lib
fi


# done
echo Done


