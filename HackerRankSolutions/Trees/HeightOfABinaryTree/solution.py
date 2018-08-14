def height(root):
    if root.left is None and root.right is None:
        return 0
    if root.left is not None and root.right is not None:
        return 1 + max([height(root.left), height(root.right)])
    if root.left is not None:
        return 1 + height(root.left)
    return 1 + height(root.right)