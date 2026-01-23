package com.eventastic.model;

import java.util.UUID;

public class AddOnOption {
<<<<<<< HEAD
    private UUID id;
=======

    private UUID optionId;
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
    private String name;
    private String description;
    private double price;

<<<<<<< HEAD
    public AddOnOption() {}

    public AddOnOption(UUID id, String name, String description, double price) {
        this.id = id;
=======
    public AddOnOption(UUID optionId, String name, String description, double price) {
        this.optionId = optionId;
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
        this.name = name;
        this.description = description;
        this.price = price;
    }

<<<<<<< HEAD
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
=======
    public double getPrice() {
        return price;
    }
}
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
