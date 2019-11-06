package br.com.ulisses.igti.gerenciador.entity.menu;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import br.com.ulisses.igti.gerenciador.entity.permission.Permission;

public class Page implements Serializable, Comparable<Page> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String label;
	private String url;
	private boolean writeSupport;
	private Long order;
	private MenuItem menuItem;
	private List<Permission> permissions;

	public Page() {
		// DEFAULT CONSTRUCTOR
	}

	public Page(Long id, String label, String url, boolean writeSupport, Long order, MenuItem menuItem,
			List<Permission> permissions) {
		this.id = id;
		this.label = label;
		this.url = url;
		this.writeSupport = writeSupport;
		this.order = order;
		this.menuItem = menuItem;
		this.permissions = permissions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isWriteSupport() {
		return writeSupport;
	}

	public void setWriteSupport(boolean writeSupport) {
		this.writeSupport = writeSupport;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Page page = (Page) o;

		return writeSupport == page.writeSupport && Objects.equals(id, page.id) && Objects.equals(label, page.label)
				&& Objects.equals(url, page.url) && Objects.equals(order, page.order)
				&& Objects.equals(menuItem, page.menuItem);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, label, url, writeSupport, order, menuItem);
	}

	@Override
	public String toString() {
		return "Page{" + "id=" + id + ", label='" + label + '\'' + ", url='" + url + '\'' + ", writeSupport="
				+ writeSupport + ", order=" + order + ", menuItem=" + menuItem + '}';
	}

	@Override
	public int compareTo(Page o) {
		return Comparator.comparing(Page::getOrder).thenComparing(Page::getLabel).compare(this, o);
	}
}
