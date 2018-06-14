package models.petmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.RandomStringUtils;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Pet{

	@JsonProperty("photoUrls")
	private List<String> photoUrls;

	@JsonProperty("name")
	private String name;

    @JsonProperty("id")
	private int id;

	@JsonProperty("category")
	private Category category;

	@JsonProperty("tags")
	private List<TagsItem> tags;

	@JsonProperty("status")
    private String status;

    public Pet() {
    }

    public Pet(int id, Category category, String name, List<String> photoUrls, List<TagsItem> tags, String status) {
        this.photoUrls = photoUrls;
        this.name = name;
        this.id = id;
        this.category = category;
        this.tags = tags;
        this.status = status;
    }

    public Pet(int idTestValue) {
    	this.id = idTestValue;
    }

	public Pet(Map<String,String> testData) {
		this.id = Integer.parseInt(RandomStringUtils.randomNumeric(5));
		this.category = new Category(Integer.parseInt(testData.get("categoryId")), testData.get("categoryName"));
		this.name = testData.get("name");
		this.photoUrls = new ArrayList<String>();
		photoUrls.add(testData.get("photoUrl"));
		this.tags = new ArrayList<TagsItem>();
		tags.add(new TagsItem(Integer.parseInt(testData.get("tagId")), testData.get("tagName")));
		this.status = testData.get("status");
	}

	public void setPhotoUrls(List<String> photoUrls){
		this.photoUrls = photoUrls;
	}

	public List<String> getPhotoUrls(){
		return photoUrls;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setTags(List<TagsItem> tags){
		this.tags = tags;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Pet{" +
			"photoUrls = '" + photoUrls + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",tags = '" + tags + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}