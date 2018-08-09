import math
import collections

def isValid(s):
    stripped = s.strip()
    freq = collections.Counter(stripped).values()
    unique = list(set(freq))
    if math.fabs(unique[0] - unique[-1]) > 1:
        return 'NO'
    if freq.count(unique[0]) == len(freq):
        return 'YES'
    if freq.count(unique[0]) == 1 or freq.count(unique[-1]) == 1:
        return 'YES'
    return 'NO'
