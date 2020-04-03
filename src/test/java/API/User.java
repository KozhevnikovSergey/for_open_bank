package API;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User{

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("avatar")
	private String avatar;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("email")
	private String email;

	@JsonProperty("job")
	private String job;

	@JsonProperty("name")
	private String name;

	@JsonProperty("createdAt")
	private String createdAt;

	public void setCreatedAt(String job){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setJob(String job){
		this.job = job;
	}

	public String getJob(){
		return job;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setId(int id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"last_name = '" + lastName + '\'' + 
			",id = '" + id + '\'' + 
			",avatar = '" + avatar + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}