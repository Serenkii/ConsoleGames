package chess;

public class Position {
	
	char characterX;
	char characterY;
	int xPos;
	int yPos;
	private static final int ASCII_a = 97;
	private static final int ASCII_1 = 49;
	
	public Position(char x, char y) {
		setPosition(x, y);
	}
	
	public Position(int x, int y) {
		setPosition(x, y);
	}

	public void setPosition(char x, char y) {
		this.characterX = x;
		this.characterY = y;
		this.xPos = x - this.ASCII_a;
		this.yPos = y - this.ASCII_1;
	}
	
	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		this.characterX = (char) (x + this.ASCII_a);
		this.characterY = (char) (y + this.ASCII_1);
	}
	
	
	@Override
	public String toString() {
		return "Position [characterX=" + characterX + ", characterY=" + characterY + ", xPos=" + xPos + ", yPos=" + yPos
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (characterX != other.characterX)
			return false;
		if (characterY != other.characterY)
			return false;
		if (xPos != other.xPos)
			return false;
		if (yPos != other.yPos)
			return false;
		return true;
	}
}
