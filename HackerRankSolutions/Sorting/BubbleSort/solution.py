def swap(arr, i1, i2):
    temp = arr[i1]
    arr[i1] = arr[i2]
    arr[i2] = temp


def countSwaps(a):
    swaps = 0
    did_swaps = True
    while did_swaps:
        did_swaps = False
        for i in range(len(a) - 1):
            if a[i] > a[i+1]:
                swap(a, i, i+1)
                swaps += 1
                did_swaps = True
    print 'Array is sorted in ' + str(swaps) + ' swaps.'
    print 'First Element: ' + str(a[0])
    print 'Last Element: ' + str(a[-1])