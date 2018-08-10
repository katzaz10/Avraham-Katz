import sys

def maxSubsetSum(arr):
    if len(arr) == 1:
        return arr[0]
    if len(arr) == 2:
        return max([arr[0], arr[1]])

    cur_max = [None]*len(arr)
    tot_max = - sys.maxint - 1
    cur_max[0] = arr[0]
    cur_max[1] = max([cur_max[0], arr[1]])
    tot_max = max([cur_max[0], cur_max[1]])
    for i in range(2, len(arr)):
        cur_max[i] = max([arr[i], tot_max, arr[i] + cur_max[i-2]])
        if cur_max[i] > tot_max:
            tot_max = cur_max[i]
    return tot_max