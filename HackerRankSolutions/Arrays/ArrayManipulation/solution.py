def arrayManipulation(n, queries):
    arr = [0] * n
    for query in queries:
        start = query[0] - 1
        end = query[1] - 1
        shift = query[2]

        arr[start] += shift
        if end < len(arr) - 1:
            arr[end + 1] -= shift

    cur = 0
    max = 0

    for i in arr:
        if i != 0:
            cur += i
            if cur > max:
                max = cur

    return max