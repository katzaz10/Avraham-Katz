def twoStrings(s1, s2):
    map = {}
    for i in s1:
        map[i] = True
    for i in s2:
        if map.setdefault(i, False):
            return 'YES'
    return 'NO'