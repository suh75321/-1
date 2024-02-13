package com.example.demo.jwt

import com.example.demo.security.MemberPrincipal
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetails

class JwtAuthenticationToken(
    private val principal: MemberPrincipal,
    details: WebAuthenticationDetails
): AbstractAuthenticationToken(principal.authorities) {

    init {
        super.setAuthenticated(true)
        super.setDetails(details)
    }

    override fun getCredentials() = null


    override fun getPrincipal() = principal

    override fun isAuthenticated(): Boolean {
        return true
    }
}