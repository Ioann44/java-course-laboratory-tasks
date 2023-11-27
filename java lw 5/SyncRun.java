public class SyncRun {
	private boolean isWriteTurn = true;

	public synchronized void waitForWrite() throws InterruptedException {
		while (!isWriteTurn) {
			wait();
		}
	}

	public synchronized void waitForRead() throws InterruptedException {
		while (isWriteTurn) {
			wait();
		}
	}

	public synchronized void switchTurn() {
		isWriteTurn = !isWriteTurn;
		notifyAll();
	}
}
