def candies(n, arr):
    candy = [1] * len(arr)
    for i in range(1, len(arr)):
        if arr[i] > arr[i-1]:
            candy[i] = candy[i-1] + 1
    for j in range(len(arr) - 2, -1, -1):
        if arr[j] > arr[j+1] and candy[j] <= candy[j+1]:
            candy[j] = candy[j+1] + 1
    return sum(candy)