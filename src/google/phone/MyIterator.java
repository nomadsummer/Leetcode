package google.phone;

import java.util.Iterator;
/*Create a filterediterator that skips over nulls. 
 * Create a class that implements iterator, takesanother iterator in its constructor.  
 * Your class iterates through theother iterator except for cases where the initial iterator has a null value. 
 * In this case your iterator skips over the null and treats it as if it did not exist.
 * 
 */
public class MyIterator<T> implements Iterator<T> {
	Iterator<T> iterator;
	T nextElem;
	
	public MyIterator(Iterator<T> sourceIterator) {
		this.iterator = sourceIterator;
		getNext();
	}
	
	private void getNext() {
		nextElem = null;
		while(iterator.hasNext()) {
			nextElem = iterator.next();
			if(nextElem != null)
				break;
		}
	}
	//cache the next one in the constructor and next() to make hasNext() simple
	@Override
	public boolean hasNext() {
		return nextElem != null;
	}
	@Override
	public T next() {
		T tmp = nextElem;
		getNext();
		return tmp;
	}
}



/*如果按照正常顺序，先调用一次hasNext(),在调用一次next()，如此循环直到结束。这样是没问题的。但是如果有人先调用hasNext()多次，再调用next()，就会出错。举个例子，假如本来的list是{a, b, c}， 然后有人调用了两次hasNext(), 接着调用next(),这时候会返回b，而实际上时候应该返回的是a才对，不能因为调用了hasNext，就影响了结果。修改后的代码应该是：
class yourIterator implements Iterator<E> {
        Iterator<E> it;
        E nextElem;
        public yourIterator(Iterator<E> sourceIterator) {
            this.it = sourceIterator;
            this.nextElem = null;
        }
        @Override
        public Integer next() {
            E temp = nextElem;
            nextElem = null;
            return tmp;
        }

        @Override
        public boolean hasNext() {
          if (nextElem != null) {
             return true;
           }
            while(it.hasNext()) {
                nextElem = it.next();
                if (nextElem != null) {
                    return true;
                }
            }
            return false;
        }
}
*/