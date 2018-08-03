def hourGlassVal(arr, x, y):
    #gets the value of an hourglass, based on the given x and y coordinates as the top-left most corner
    return sum(arr[y][x:x + 3]) + sum(arr[y + 2][x:x + 3]) + arr[y+1][x+1]


def hourglassSum(arr):
    max = None
    for x in range(4):
        for y in range(4):
            sum = hourGlassVal(arr, x, y)
            if max is None or sum > max:
                max = sum
    return max
