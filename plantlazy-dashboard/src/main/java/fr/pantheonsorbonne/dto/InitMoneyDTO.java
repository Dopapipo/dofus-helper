package fr.pantheonsorbonne.dto;

public class InitMoneyDTO {

    private Double money;

    public InitMoneyDTO() {
    }

    public InitMoneyDTO(Double money) {
        this.money = money;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "InitMoneyDTO{" +
                "money=" + money +
                '}';
    }
}
