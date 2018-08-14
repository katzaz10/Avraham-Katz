def lca(root, v1, v2):
    if v1 == root.info or v2 == root.info:
        return root
    if v1 > root.info:
        if v2 < root.info:
            return root
        return lca(root.right, v1, v2) #v1 and v2 are greater than root, so go right
    #v1 is less than root
    if v2 > root.info:
        return root
    return lca(root.left, v1, v2) #v1 and v2 are both less than root, so go left
