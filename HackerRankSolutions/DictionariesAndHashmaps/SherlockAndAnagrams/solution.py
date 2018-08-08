def substringsfromstart(s):
    '''Finds all substrings starting at index 0.
    Example: 'abc' --> ['a', 'ab', 'abc']'''
    list = []
    cur_word = ''
    for i in range(len(s)):
        list.append(cur_word + s[i])
        cur_word += s[i]
    return list

def order(s):
    '''Orders a string'''
    return ''.join(sorted(s))

def summation(n):
    '''Finds the summation of n'''
    if n == 0:
        return 0

    if n == 1:
        return 1

    return n + summation(n - 1)


def sherlockAndAnagrams(s):
    dict = {}
    for i in range(len(s)):
        substring = s[i:]
        substring_words = substringsfromstart(substring)
        for word in substring_words:
            orderedword = order(word)
            dict[orderedword] = dict.setdefault(orderedword, 0) + 1
    anagrams = 0
    for word in dict:
        anagrams += summation(dict[word] - 1)
    return anagrams