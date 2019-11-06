package br.com.ulisses.igti.gerenciador.entity.permission;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

import br.com.ulisses.igti.gerenciador.entity.menu.Page;

public class Permission implements Serializable, Comparable<Permission> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Page page;
	private String groupLdap;
	private String username;
	private boolean writeSupport;

	public Permission() {
		// DEFAULT CONSTRUCTOR
	}

	public Permission(Long id, Page page, String groupLdap, String username, boolean writeSupport) {
		this.id = id;
		this.page = page;
		this.groupLdap = groupLdap;
		this.username = username;
		this.writeSupport = writeSupport;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getGroupLdap() {
		return groupLdap;
	}

	public void setGroupLdap(String groupLdap) {
		this.groupLdap = groupLdap;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isWriteSupport() {
		return writeSupport;
	}

	public void setWriteSupport(boolean writeSupport) {
		this.writeSupport = writeSupport;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Permission that = (Permission) o;

		return writeSupport == that.writeSupport && Objects.equals(id, that.id) && Objects.equals(page, that.page)
				&& Objects.equals(groupLdap, that.groupLdap) && Objects.equals(username, that.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, page, groupLdap, username, writeSupport);
	}

	@Override
	public String toString() {
		return "Permission{" + "id=" + id + ", page=" + page + ", groupLdap='" + groupLdap + '\'' + ", username='"
				+ username + '\'' + ", writeSupport=" + writeSupport + '}';
	}

	@Override
	public int compareTo(Permission o) {
		return Comparator.comparing(Permission::getUsername, Comparator.nullsLast(String::compareToIgnoreCase))
				.thenComparing(Permission::getGroupLdap, Comparator.nullsLast(String::compareToIgnoreCase))
				.compare(this, o);
	}
}
