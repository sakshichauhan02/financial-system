package com.example.financialsystem;

import jakarta.persistence.*;
import java.util.List;

public class FinancialSystemEntities {

    @Entity
    public static class FinancialAdvisor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long advisorId;

        private String name;
        private String email;
        private String phoneNumber;

        @OneToMany(mappedBy = "advisor")
        private List<Client> clients;

        public Long getAdvisorId() { return advisorId; }
        public void setAdvisorId(Long advisorId) { this.advisorId = advisorId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhoneNumber() { return phoneNumber; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

        public List<Client> getClients() { return clients; }
        public void setClients(List<Client> clients) { this.clients = clients; }
    }

    @Entity
    public static class Client {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long clientId;

        private String name;
        private String email;

        @ManyToOne
        @JoinColumn(name = "advisor_id")
        private FinancialAdvisor advisor;

        @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
        private Portfolio portfolio;

        public Long getClientId() { return clientId; }
        public void setClientId(Long clientId) { this.clientId = clientId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public FinancialAdvisor getAdvisor() { return advisor; }
        public void setAdvisor(FinancialAdvisor advisor) { this.advisor = advisor; }

        public Portfolio getPortfolio() { return portfolio; }
        public void setPortfolio(Portfolio portfolio) { this.portfolio = portfolio; }
    }

    @Entity
    public static class Portfolio {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long portfolioId;

        private Double totalValue;

        @OneToOne
        @JoinColumn(name = "client_id")
        private Client client;

        @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
        private List<Security> securities;

        public Long getPortfolioId() { return portfolioId; }
        public void setPortfolioId(Long portfolioId) { this.portfolioId = portfolioId; }

        public Double getTotalValue() { return totalValue; }
        public void setTotalValue(Double totalValue) { this.totalValue = totalValue; }

        public Client getClient() { return client; }
        public void setClient(Client client) { this.client = client; }

        public List<Security> getSecurities() { return securities; }
        public void setSecurities(List<Security> securities) { this.securities = securities; }
    }

    @Entity
    public static class Security {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long securityId;

        private String name;
        private String type;
        private Double price;

        @ManyToOne
        @JoinColumn(name = "portfolio_id")
        private Portfolio portfolio;

        public Long getSecurityId() { return securityId; }
        public void setSecurityId(Long securityId) { this.securityId = securityId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }

        public Portfolio getPortfolio() { return portfolio; }
        public void setPortfolio(Portfolio portfolio) { this.portfolio = portfolio; }
    }
}
