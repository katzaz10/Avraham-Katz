def rotLeft(a, d):
    d = d % len(a)
    return a[d:] + a[:d]


