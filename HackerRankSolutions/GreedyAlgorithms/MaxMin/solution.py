import sys

def maxMin(k, arr):
    arr.sort()
    minval = sys.maxint
    for i in range(len(arr) - k + 1):
        delta = arr[i+k-1] - arr[i]
        if delta < minval:
            minval = delta
    return minval