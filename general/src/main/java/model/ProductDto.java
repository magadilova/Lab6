package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;


import java.io.Serializable;


@XStreamAlias("product")
public class ProductDto implements Comparable<Product>, Serializable {
    private String name = null; //Поле не может быть null, Строка не может быть пустой
    @XStreamAlias("coordinates")
    private Coordinates coordinates = null; //Поле не может быть null
    private double price = 0; //Значение поля должно быть больше 0
    private String partNumber = null; //Поле может быть null
    private long manufactureCost = 0;
    @XStreamAlias("unitOfMeasure")
    private UnitOfMeasure unitOfMeasure = null; //Поле может быть null
    @XStreamAlias("person")
    private Person owner = null; //Поле не может быть null

    public ProductDto(String name, Coordinates coordinates, double price, String partNumber, long manufactureCost, UnitOfMeasure unitOfMeasure, Person owner) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public long getManufactureCost() {
        return manufactureCost;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public ProductDto() {
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Person getOwner() {
        return owner;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setManufactureCost(long manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public String toString() {
        return "Product :\n " +
                "Name : '" + name + '\'' +
                ",\n Coordinates :" + coordinates.toString() +
                ",\n Price : " + price +
                ",\n Part number : '" + partNumber + '\'' +
                ",\n Manufacture cost : " + manufactureCost +
                ",\n Unit of measure : " + unitOfMeasure +
                ",\n Owner :  " + owner.toString();
    }

    @Override
    public int compareTo(Product o) {
        return name.compareTo(o.getName());
    }
}
