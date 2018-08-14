def checkBST(root):
    return checknode(root, float('-inf'), float('inf'))

def checknode(node, min, max):
    if node is None:
        return True
    if node.data <= min or node.data >= max:
        return False
    return checknode(node.left, min, node.data) and checknode(node.right, node.data, max)