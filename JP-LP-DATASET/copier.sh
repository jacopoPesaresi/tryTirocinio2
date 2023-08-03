NUM=$1


echo "# "$NUM 
for DIR in `ls`;
do
mkdir ../JPdataset/$NUM
mkdir ../JPdataset/$NUM/img
cp $DIR/$NUM/img/* ../JPdataset/$NUM/img

mkdir ../JPdataset/$NUM
mkdir ../JPdataset/$NUM/label
cp $DIR/$NUM/label/* ../JPdataset/$NUM/label
done;
