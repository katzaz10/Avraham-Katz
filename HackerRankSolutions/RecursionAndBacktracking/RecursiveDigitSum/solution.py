def superDigit(n, k):
    integer = sum(map(int, list(n)))
    if k==1:
        return reducetodigit(integer)
    else:
        return reducetodigit(integer*k)

def reducetodigit(integer):
    if integer<10:
        return integer
    else:
        return reducetodigit(sum(map(int, list(str(integer)))))