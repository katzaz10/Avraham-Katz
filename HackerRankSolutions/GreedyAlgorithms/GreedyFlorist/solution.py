def getMinimumCost(k, c):
    c.sort()
    multiple = 1
    round = 0
    total = 0
    for i in range(len(c) - 1, -1, -1):
        total += c[i] * multiple
        round += 1
        if round % k == 0:
            multiple += 1
    return total