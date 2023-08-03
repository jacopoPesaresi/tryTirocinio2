MOD=$1

if [[ $MOD == "ALL" ]]; then
rm JPdataset_allin/*/*
rm -r JPdataset/*;
elif [[ $MOD == "IN" ]]; then
rm JPdataset_allin/*/*;
else
echo "inserire ALL (pulisco il JPdataset e JPdataset_allin) o IN (solo il secondo)"
fi
