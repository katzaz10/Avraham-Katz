def triplets(a, b, c):
    a,b,c = list(sorted(set(a))), list(sorted(set(b))), list(sorted(set(c)))
    ai, bi, ci = 0, 0, 0
    ans = 0
    while bi < len(b):
        while ai < len(a) and a[ai] <= b[bi]:
            ai += 1
        while ci < len(c) and c[ci] <= b[bi]:
            ci += 1
        ans += ai * ci
        bi += 1
    return ans