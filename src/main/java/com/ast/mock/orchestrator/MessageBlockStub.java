package com.ast.mock.orchestrator;

import com.cobiscorp.cobis.cts.domains.IMessageBlock;

public class MessageBlockStub implements IMessageBlock {
	int messageNumber;
	String messageText;
	int type;

	public MessageBlockStub(int messageNumber, String messageText, int type) {
		this.messageNumber = messageNumber;
		this.messageText = messageText;
		this.type = type;
	}

	public int getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		MessageBlockStub that = (MessageBlockStub) o;

		if (messageNumber != that.messageNumber) {
			return false;
		}
		if (type != that.type) {
			return false;
		}
		return messageText != null ? messageText.equals(that.messageText) : that.messageText == null;
	}

	@Override
	public int hashCode() {
		int result = messageNumber;
		result = 31 * result + (messageText != null ? messageText.hashCode() : 0);
		result = 31 * result + type;
		return result;
	}

	@Override public String toString() {
		return "MessageBlockStub{" +
				"messageNumber=" + messageNumber +
				", messageText='" + messageText + '\'' +
				", type=" + type +
				'}';
	}
}
