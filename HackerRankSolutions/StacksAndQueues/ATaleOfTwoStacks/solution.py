class MyQueue(object):
    def __init__(self):
        self.on = []
        self.off = []

    def move(self, stack1, stack2):
        for i in range(len(stack1)):
            stack2.append(stack1.pop())

    def peek(self):
        if not self.off:
            self.move(self.on, self.off)
        return self.off[len(self.off) - 1]

    def pop(self):
        if not self.off:
            self.move(self.on, self.off)
        return self.off.pop()

    def put(self, value):
        self.on.append(value)