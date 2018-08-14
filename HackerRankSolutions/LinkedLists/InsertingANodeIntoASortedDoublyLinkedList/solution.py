def sortedInsert(head, data):
    insert = DoublyLinkedListNode(data)
    if head is None:
        return insert
    if data < head.data:
        insert.next = head
        return insert
    cur = head
    while cur is not None:
        if cur.data <= data and (cur.next is None or cur.next.data > data):
            insert.next = cur.next
            cur.next = insert
            return head
        cur = cur.next