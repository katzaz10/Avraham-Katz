def substringsfromstart(s):
    '''Takes a string and finds all substrings i-?
    Example: if s == 'abc' returns ['a', 'ab', 'abc']'''

    list = []
    cur_word = ''
    for i in range(len(s)):
        list.append(cur_word + s[i])
        cur_word += s[i]
    return list


def order(s):
    '''Orders are a string in ascending order'''

    return ''.join(sorted(s))


def summation(n):
    '''Sums all values n, n-1, n-2, 0'''

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