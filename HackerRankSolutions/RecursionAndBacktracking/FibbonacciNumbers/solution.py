def fib(n, dict):
    if dict.get(n, None) is not None:
        return dict[n]
    return fib(n - 1, dict) + fib (n - 2, dict)

def fibonacci(n):
    dict = {}
    dict[0] = 0
    dict[1] = 1
    return fib(n, dict)