import math

def swap(arr, i1, i2):
    '''swap index i1 and i2 in an array'''
    temp = arr[i1]
    arr[i1] = arr[i2]
    arr[i2] = temp

def minimumBribes(q):
    for i in range(len(q)):
        if q[i] > i:
            if math.fabs(q[i] - i) > 3: #if any index is too far from the value of the index, the line is too chaotic
                print 'Too chaotic'
                return

    swaps = 0
    for i in range(1, len(q)):
        if q[i] < q[i-1]:
            j = i
            while j > 0 and q[j] < q[j - 1]:
                swap(q, j, j-1)
                swaps += 1
                j -= 1
    print swaps