package com.kgisl.docket;

import com.kgisl.docket.model.Portfolio;


/**
 * PortfolioBuilder
 */
public class PortfolioBuilder {

    private Portfolio portfolioObj = new Portfolio();

    public PortfolioBuilder id(Long id) {
        portfolioObj.setId(id);
        return this;
    }

    public PortfolioBuilder cat(String cat) {
        portfolioObj.setCat(cat);
        return this;
    }

    public PortfolioBuilder symbol(String symbol) {
        portfolioObj.setSymbol(symbol);
        return this;
    }


    public Portfolio build() {
        return portfolioObj;
    }

}