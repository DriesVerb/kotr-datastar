package com.example.html

import kotlinx.html.*

data class NavItem(
    val label: String,
    val link: String,
)

fun FlowContent.navbar(
    title: String,
    mainLink: NavItem = NavItem("Link", "/link"),
    dropdownTitle: String = "Parent",
    dropdownItems: List<NavItem> = listOf(NavItem("Link 1", "/link1"), NavItem("Link 2", "/link2")),
) {
    div("navbar bg-base-100 shadow-sm") {
        div("flex-1") {
            a(classes = "btn btn-ghost text-xl") {
                +title
            }
        }
        div("flex-none") {
            ul("menu menu-horizontal px-1") {
                linkedAnchor(link = mainLink.link, label = mainLink.label)
                li {
                    details {
                        summary { +dropdownTitle }
                        ul("bg-base-100 rounded-t-none p-2") {
                            dropdownItems.forEach { item ->
                                linkedAnchor(link = item.link, label = item.label)
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Use 'UL' as the receiver so that 'li' is available.
 */
fun UL.linkedAnchor(
    link: String,
    label: String,
) {
    li {
        a {
            href = link
            +label
        }
    }
}
