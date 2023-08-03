NUM=$1


echo "# "$NUM 
for DIR in `ls JPdataset`;
do

cp JPdataset/$DIR/img/* JPdataset_allin/img
cp JPdataset/$DIR/label/* JPdataset_allin/label


done;
