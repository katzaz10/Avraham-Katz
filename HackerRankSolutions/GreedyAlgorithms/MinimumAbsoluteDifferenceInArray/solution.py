def minimumAbsoluteDifference(arr):
    min = sys.maxint
    arr.sort()
    for i in range(len(arr) - 1):
        dif = math.fabs(arr[i] - arr[i+1])
        if dif < min:
            min = dif
    return int(min)