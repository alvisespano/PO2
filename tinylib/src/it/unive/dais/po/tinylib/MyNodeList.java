package it.unive.dais.po.tinylib;

public class MyNodeList<E> implements MyList<E> {

    private MyNode<E> head;
    private int size;

    public MyNodeList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void insertHead(E data) {
        try {
            insertAt(0, data);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertAt(int position, E data) throws NotFoundException {
        if (position < 0 || position > size()) throw new NotFoundException("MyNodeList.insertAt(): cannot insert at position " + position);
        if (position == 0)
            head = new MyNode<E>(data, head);
        else {
            MyNode<E> n = head;
            while (position-- > 1) {
                n = n.getNext();
            }
            n.setNext(new MyNode<E>(data, n.getNext()));
        }
        size++;
    }

    public void removeHead() throws NotFoundException {
        removeAt(0);
    }

    public void removeAt(int position) throws NotFoundException {
        if (position < 0 || position >= size()) throw new NotFoundException("MyNodeList.removeAt(): cannot remove at position " + position);
        if (position == 0)
            head = head.getNext();
        else {
            MyNode<E> n = head;
            while (position-- > 1) {
                n = n.getNext();
            }
            n.setNext(n.getNext().getNext());
        }
        size--;
    }

    public int find(E data) throws NotFoundException {
        MyNode<E> n = head;
        int i = 0;
        while (!n.getData().equals(data)) {
            n = n.getNext();
            if (++i > size()) throw new NotFoundException("MyNodeList.find(): cannot find the required element");
        }
        return i;
    }

    public E getHead() throws NotFoundException {
        return getAt(0);
    }

    public E getAt(int position) throws NotFoundException {
        if (position < 0 || position >= size()) throw new NotFoundException("MyNodeList.getAt(): cannot get element at position " + position);
        if (position == 0)
            return head.getData();
        else {
            MyNode<E> n = head;
            while (position-- > 1) {
                n = n.getNext();
            }
            return n.getNext().getData();
        }
    }


    @Override
    public MyIterator<E> iterator() {
        return new MyIterator<E>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size();
            }

            @Override
            public E next() {
                try {
                    return getAt(pos++);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                    throw new RuntimeException("iterator.next() failed");
                }
            }
        };
    }
}
