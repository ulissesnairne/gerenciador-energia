package br.com.ulisses.igti.gerenciador.entity.menu;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MenuItem implements Serializable, Comparable<MenuItem> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String domain;
	private String label;
	private Long order;
	private List<Page> pages;

	public MenuItem() {
		// DEFAULT CONSTRUCTOR
	}

	public MenuItem(Long id, String domain, String label, Long order, List<Page> pages) {
		this.id = id;
		this.domain = domain;
		this.label = label;
		this.order = order;
		this.pages = pages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		MenuItem menuItem = (MenuItem) o;

		return Objects.equals(id, menuItem.id) && Objects.equals(domain, menuItem.domain)
				&& Objects.equals(label, menuItem.label) && Objects.equals(order, menuItem.order);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, domain, label, order);
	}

	@Override
	public String toString() {
		return "MenuItem{" + "id=" + id + ", domain='" + domain + '\'' + ", label='" + label + '\'' + ", order=" + order
				+ '}';
	}

	@Override
	public int compareTo(MenuItem o) {
		return Comparator.comparing(MenuItem::getOrder).thenComparing(MenuItem::getLabel).compare(this, o);
	}
}
