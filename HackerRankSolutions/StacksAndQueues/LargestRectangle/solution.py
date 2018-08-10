def peek(arr):
    '''Peeks the top value in a stack implemented as an array'''
    return arr[len(arr) - 1]

def largestRectangle(h):
    index = [0] #starts at 0 so peek never throws index out of bound
    height = [0] #starts at 0 so peek never throws index out of bound
    max_area = 0
    for i in range(len(h)):
        if h[i] > peek(height):
            index.append(i)
            height.append(h[i])
        elif h[i] < peek(height):
            previndex = i
            while h[i] < peek(height):
                previndex = index.pop()
                span = i - previndex
                area = height.pop() * span
                if area > max_area:
                    max_area = area
            height.append(h[i])
            index.append(previndex)
    i += 1
    while height:
        span = i - index.pop()
        area = height.pop() * span
        if area > max_area:
            max_area = area
    return max_area
