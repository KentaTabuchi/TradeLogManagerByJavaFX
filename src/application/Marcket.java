/**
 * 
 */
package application;

/**
 * @author misskabu
 *
 */
public enum Marcket {
	TSE1("東証1部"), //The First Section of the Tokyo Stock Exchange
	TSE2("東証2部"),
	JASDAC("JSQ"),
	MOTHERS("マザーズ"),
	OTHER("その他");
	
	private final String text;
	
	private Marcket(final String text){
		this.text = text;
	}
	public String getString(){
		return this.text;
	}
}
