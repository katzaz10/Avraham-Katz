def pairs(k, arr):
    pairs = 0
    freq = {}
    for a in arr:
        freq[a] = freq.get(a, 0) + 1
    vals = freq.keys()
    for val in vals:
        pairs += freq.get(val - k, 0)
    return pairs