def isBalanced(s):
    if len(s) % 2 != 0:
        return 'NO'
    if s == '':
        return 'YES'

    comp = {'(':')', '{':'}', '[':']'}
    index = 0
    while index < len(s) - 1:
        if s[index] == ')' or s[index] == ']' or s[index] == '}':
            return 'NO'
        if comp[s[index]] == s[index + 1]:
            s = s[:index] + s[index+2:]
            if index != 0:
                index -= 1
            if s == '':
                return 'YES'
        else:
            index += 1

    return 'NO'