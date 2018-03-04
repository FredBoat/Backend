/*
 * MIT License
 *
 * Copyright (c) 2016-2018 The FredBoat Org https://github.com/FredBoat/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.fredboat.quarterdeck.backend.rest;

import com.fredboat.quarterdeck.backend.Application;
import fredboat.db.entity.main.Prefix;
import fredboat.db.repositories.api.PrefixRepo;
import fredboat.db.repositories.impl.rest.RestPrefixRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.npstr.sqlsauce.entities.GuildBotComposite;

import javax.annotation.Nullable;

/**
 * Created by napster on 17.02.18.
 */
@RestController
@RequestMapping("/" + Application.API_VERSION + RestPrefixRepo.PATH)
public class PrefixController extends EntityController<GuildBotComposite, Prefix> implements PrefixRepo {

    protected final PrefixRepo prefixRepo;

    public PrefixController(PrefixRepo repo) {
        super(repo);
        this.prefixRepo = repo;
    }

    @Nullable
    @PostMapping("/getraw")
    @Override
    public String getPrefix(@RequestBody GuildBotComposite id) {
        return prefixRepo.getPrefix(id);
    }
}
