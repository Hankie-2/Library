package util;

public enum accType {

	 READER(1),
	 LIBRARIAN(2);
	
	 public final Integer number;
	
	private accType(Integer number) {
        this.number = number;
    }
}
